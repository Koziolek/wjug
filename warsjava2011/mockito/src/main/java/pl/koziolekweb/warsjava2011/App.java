package pl.koziolekweb.warsjava2011;

import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.ExecutionException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class App {

	public static final int START = 0;
	public static final int STOP = 10;

	public static void main(String[] args) {

		QuestionnairePanel questionPanel = new QuestionnairePanel();
		ProgressPanel progressPanel = new ProgressPanel(START, STOP);
		DeepThought deepThought = new DeepThought(questionPanel, progressPanel);

		ButtonPanel buttonPanel = new ButtonPanel(deepThought);

		JPanel cp = new JPanel();
		LayoutManager layout = new BoxLayout(cp, BoxLayout.Y_AXIS);
		cp.setLayout(layout);
		cp.add(questionPanel);
		cp.add(buttonPanel);
		cp.add(progressPanel);

		Toolkit toolkit = Toolkit.getDefaultToolkit();

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(cp);
		frame.pack();
		frame.setLocation(toolkit.getScreenSize().width / 2,
				toolkit.getScreenSize().height / 2);

		frame.setVisible(true);
	}
}

interface SecurityProvider {

	boolean login();
}

class ButtonPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	SecurityProvider securityProvider;

	public ButtonPanel() {
		securityProvider = Mockito.mock(SecurityProvider.class);
		Mockito.when(securityProvider.login()).thenAnswer(new Answer<Boolean>() {

			@Override
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				int read = System.in.read();
				return true;
			}
		});
	}

	private class StartAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (securityProvider.login())
				deepThought.findAnswer();
		}
	}

	private class StopAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			deepThought.cancel();
		}
	}

	private DeepThought deepThought;

	public ButtonPanel(final DeepThought deepThought) {
		this();
		this.deepThought = deepThought;
		JButton startButton = new JButton("Start");
		startButton.addActionListener(new StartAction());

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new StopAction());

		add(startButton);
		add(cancelButton);
	}
}

class DeepThought {

	private class TaskListener implements PropertyChangeListener {
		public void propertyChange(PropertyChangeEvent evt) {
			if ("progress".equals(evt.getPropertyName())) {
				progressPresenter.progress((Integer) evt.getNewValue());
			}
		}
	}

	private ProgressPresenter progressPresenter;

	private QuestionnairePresenter questionnairePresenter;

	private DeepThoughtTask task;

	public DeepThought(QuestionnairePresenter questionnairePresenter,
			ProgressPresenter progressPresenter) {
		this.questionnairePresenter = questionnairePresenter;
		this.progressPresenter = progressPresenter;

	}

	public void cancel() {
		if (task != null)
			task.cancel(true);
	}

	public void findAnswer() {
		task = new DeepThoughtTask(questionnairePresenter);
		task.addPropertyChangeListener(new TaskListener());
		task.execute();
	}
}

class DeepThoughtTask extends SwingWorker<String, Integer> {

	private int DELAY = 1000;

	private QuestionnairePresenter presenter;

	public DeepThoughtTask(QuestionnairePresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	protected String doInBackground() throws Exception {
		int i = App.START;
		while (!isCancelled() && i < App.STOP) {
			i++;
			publish(new Integer[] { i });
			setProgress(App.STOP * i / App.STOP);
			Thread.sleep(DELAY);
		}
		return "42";
	}

	@Override
	protected void done() {
		if (isCancelled()) {
			presenter.setAnswer("Give up.");
		} else {
			try {
				presenter.setAnswer(get());
			} catch (InterruptedException e) {
			} catch (ExecutionException e) {
			}
		}
	}
}

class ProgressPanel extends JProgressBar implements ProgressPresenter {

	private static final long serialVersionUID = 1L;

	public ProgressPanel(int min, int max) {
		super(min, max);
	}

	@Override
	public void progress(int progress) {
		setValue(progress);
	}
}

interface ProgressPresenter {
	void progress(int progress);
}

class QuestionnairePanel extends JPanel implements QuestionnairePresenter {

	private static final long serialVersionUID = 1L;

	private JLabel answerField;

	private JTextField questionField;

	public QuestionnairePanel() {
		super();
		LayoutManager questionLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(questionLayout);
		JLabel questionLabel = new JLabel("Ask your question:");
		questionField = new JTextField();
		JLabel answerLabel = new JLabel("The answer is:");
		answerField = new JLabel(" ");

		add(questionLabel);
		add(questionField);
		add(answerLabel);
		add(answerField);
	}

	public String getQuenstion() {
		return questionField.getText();
	}

	public void setAnswer(String answer) {
		answerField.setText(answer);
	}
}

interface QuestionnairePresenter {
	String getQuenstion();

	void setAnswer(String answer);
}