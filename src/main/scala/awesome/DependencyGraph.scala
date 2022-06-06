package awesome

object DependencyGraph:
  lazy val env =
    myAwesomeService.MyAwesomeService.live ++
      myConsole.Console.live
