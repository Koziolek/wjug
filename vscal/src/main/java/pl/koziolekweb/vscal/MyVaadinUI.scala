package pl.koziolekweb.vscal

import com.vaadin.ui.{Label, Button, VerticalLayout, UI}
import com.vaadin.server.VaadinRequest
import pl.koziolekweb.vscal.ButtonImplicits._
import com.vaadin.ui.Button.ClickEvent
import com.vaadin.data.Property
import com.vaadin.data.Property.ValueChangeEvent

/**
 * Created with IntelliJ IDEA.
 * User: koziolek
 * Date: 08.05.13
 * Time: 18:28
 * To change this template use File | Settings | File Templates.
 */
class MyVaadinUI extends UI {

  protected def init(request: VaadinRequest) {
    val layout: VerticalLayout = new VerticalLayout
    layout.setMargin(true)
    setContent(layout)
    val button: Button = new Button("Click Me")
    def x(n:ClickEvent): Unit = {
      println(n)
      val l = new Label("Thank you for clicking")
      l.addValueChangeListener(new Property.ValueChangeListener{
        def valueChange(p1: ValueChangeEvent) {

        }
      })
      layout.addComponent(l)
    }
    button.addClickListener(x _)
    layout.addComponent(button)
  }
}
