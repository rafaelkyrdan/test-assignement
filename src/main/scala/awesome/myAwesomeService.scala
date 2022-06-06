package awesome

import java.io.IOException
import java.time.LocalDateTime

import zio.*

object myAwesomeService:
  type MyAwesomeService = Has[MyAwesomeService.Service]
  object MyAwesomeService:
    trait Service:
      def run: ZIO[myConsole.Console, IOException, Unit]

    lazy val any: ZLayer[MyAwesomeService, Nothing, MyAwesomeService] =
      ZLayer.requires

    lazy val live: ZLayer[Any, Nothing, MyAwesomeService] =
      ZLayer.succeed(make)

    lazy val make: Service =
      new:
        override lazy val run: ZIO[myConsole.Console, IOException, Unit] =
          for
            _ <- myConsole.putStrLn("Hey! What is favorite color for output(red, yellow, green)?")
            _ <- myConsole.putStrLn("Type 'ANYTHING ELSE' if you wanna exit program")
            color <- myConsole.getStrLn
            ansiColor = color match {
              case "red"    => "\u001b[31m"
              case "yellow" => "\u001b[33m"
              case "green"  => "\u001b[32m"
              case _        => throw new RuntimeException("oh no!")
            }
            _ <- myConsole.putStrLn(s"$ansiColor[$color] ${LocalDateTime.now()}" + "\u001b[0m")
          yield ()

  lazy val run: ZIO[MyAwesomeService & myConsole.Console, IOException, Unit] =
    ZIO.accessM(_.get.run)
