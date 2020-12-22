// This will listout all the installed plugins details
Jenkins.instance.pluginManager.plugins.each {
  plugin ->
  println "${plugin.getShortName()}:${plugin.getVersion()}"
    //println ("${plugin.getDisplayName()} (${plugin.getShortName()}): ${plugin.getVersion()}")
}