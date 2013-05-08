package pl.koziolekweb.vscal

import com.vaadin.ui.Button
import com.vaadin.ui.Button.ClickEvent

/**
 * Created with IntelliJ IDEA.
 * User: koziolek
 * Date: 08.05.13
 * Time: 18:27
 * To change this template use File | Settings | File Templates.
 */
object ButtonImplicits {

  implicit def toButtonClickListener(m: ClickEvent => Unit) = new Button.ClickListener {
    def buttonClick(p1: ClickEvent) {
      m(p1)
    }
  }

}

