import zio.console.{ Console, putStrLn }
import zio.*
import awesome.myAwesomeService
import awesome.DependencyGraph

object Main extends App:
  override def run(args: List[String]): ZIO[Console, Nothing, ExitCode] =
    program
      .forever
      .catchAll { case _: Throwable => putStrLn("") *> putStrLn("Bye") }
      .exitCode

  lazy val program =
    myAwesomeService.run.provideLayer(DependencyGraph.env)
