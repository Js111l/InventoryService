
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import controller.InventoryController
import db.ProductRepository
import service.InventoryService
object Main extends App {
  implicit val system: ActorSystem = ActorSystem("main")

  private val service = new InventoryService(new ProductRepository())
  private val routes = new InventoryController(service).routes
  //
  //    val userRepository = new UserRepository(db)
  //
  //    val init = userRepository.init();
  //    Await.result(init, Duration.Inf);
  //
  //    val insertionResult = userRepository.addUser(User(1, "Janusz", "email@wp.pl"))
  //    Await.result(insertionResult, Duration.Inf)
  //
  //    val result = userRepository.getAllUsers();
  //
  //    result.onComplete {
  //      case Failure(exception) => println("2137 error " + exception)
  //      case Success(value) => println(value)
  //    }(ExecutionContext.Implicits.global)


  val server = Http().newServerAt("localhost", 9090).bind(routes)
}