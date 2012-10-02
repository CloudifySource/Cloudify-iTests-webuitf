import framework.utils.usm.StringWrapper
import Helper

service {
	name "kitchensink-service"
	type "UNDEFINED"
	
	url "http://" + InetAddress.localHost.hostName + ":7777"
	def applicationName = context.applicationName
	lifecycle{
		// DO NOT CHANGE THE PRINTOUTS - SGTEST LOOKS FOR THEM!
		init {
			 println "init fired ${var1}" 
			 println new StringWrapper("init external class")
			 println "init " + new Helper().someField;
			 println "application name is: " + applicationName;
			}
		preInstall{ println "preInstall fired ${var2}"}
		install "install.groovy"
		postInstall{ println "postInstall fired " + var1 }
		preStart{ println "preStart fired " + var2 }
		start ([ "Linux": "run.sh -dieOnParentDeath false -port 7777" ,
					"Win.*": "run.bat -dieOnParentDeath false -port 7777" ])

		postStart "post_start.groovy"

		preStop ([
			"pre_stop.groovy",
			"true",
			"String_with_Spaces",
			"1234"
		])
		postStop{ println "postStop fired" }
		shutdown {
			println "shutdown fired"
			sleep 15000 // sleep so that the test can pick up the event printouts from the log
		}

		details(["stam":{"HA HA HAAAAAAAAAAAAAAAAAAA"},
			"SomeKey":{"22222222222222222222222222"}])
		monitors (["NumberTwo":{return 2},
			"NumberOne":{return "1"}])

	}


	customCommands ([
				"cmd1" : { println "This is the cmd1 custom command"},
				"cmd2" : { throw new Exception("This is the cmd2 custom command - This is an error test")},
				"cmd3" : { "This is the cmd3 custom command. Service Dir is: " + context.serviceDirectory },
				"cmd4" : "context_command.groovy",
				"cmd5" : {x, y -> return ("this is the custom parameters command. expecting 123: "+1+x+y)},
				"cmd6" : "someScript.groovy",
				"cmd7" : {x -> "Single parameter test:parameter=" + x},
				"cmd8" : "errorScript.groovy",
				"cmd9" : { new AntBuilder().sequential { echo ("Hello World")}; return null }
			])


	plugins ([
		plugin {
			name "portLiveness"
			className "org.cloudifysource.usm.liveness.PortLivenessDetector"
			config ([
						"Port" : [7777],
						"TimeoutInSeconds" : 60,
						"Host" : "127.0.0.1"
					])
		},
		plugin{

			name "JMX Metrics"

			className "org.cloudifysource.usm.jmx.JmxMonitor"

			config ([

//						"Details" : [
//							"org.openspaces.usm.examples.simplejavaprocess:type=SimpleBlockingJavaProcess",
//							"Details"
//						],
//						"Counter" : [
//							"org.openspaces.usm.examples.simplejavaprocess:type=SimpleBlockingJavaProcess",
//							"Counter"
//						],
//						"Type" : [
//							"org.openspaces.usm.examples.simplejavaprocess:type=SimpleBlockingJavaProcess",
//							"Type"
//						],
						port : 9999
					])
		},
		plugin{

			name "JMX Details"

			className "org.cloudifysource.usm.jmx.JmxDetails"

			config ([

//						"Details" : [
//							"org.openspaces.usm.examples.simplejavaprocess:type=SimpleBlockingJavaProcess",
//							"Details"
//						],
//						"Counter" : [
//							"org.openspaces.usm.examples.simplejavaprocess:type=SimpleBlockingJavaProcess",
//							"Counter"
//						],
//						"Type" : [
//							"org.openspaces.usm.examples.simplejavaprocess:type=SimpleBlockingJavaProcess",
//							"Type"
//						],
						port : 9999
					])
		}
	])

	customProperties ([
				"TailerInterval": "1"
			])
	
	userInterface load("ui.groovy")

}