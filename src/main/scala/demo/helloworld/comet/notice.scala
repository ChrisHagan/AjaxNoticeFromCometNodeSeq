package demo.helloworld.comet

import net.liftweb.http._
import SHtml._
import js._
import net.liftweb.actor._

object NoticeServer extends LiftActor with ListenerManager{
    def createUpdate = "gogogo"
    override def lowPriority={
        case _ => println("Message came through NoticeServer")
    }
}
class NoticeEmitter extends CometActor with CometListener{
	println("This print was produced by the constructor of NoticeProducer")
	notice("This notice was produced by the constructor of NoticeProducer")
	def registerWith = NoticeServer
	override def lowPriority = {
        case _ =>{
            notice("This is a notice produced by messaging me at low priority")
            reRender(false)
        }
    }
    def render = ajaxButton("Click this to publish a lift notice",()=>{
    	    notice("This is a notice produced by click the publish a notice button")
            JsCmds.Noop
        })
    override def fixedRender = <p>This is the comet actor's position</p>
}
