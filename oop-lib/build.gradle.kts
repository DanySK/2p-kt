kotlin {

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(kotlin("reflect"))
                api(project(":solve"))
            }
        }

        val commonTest by getting {
            dependsOn(commonMain)
        }

        jvm {
            val main = compilations["main"]
            val test = compilations["test"]

            main.defaultSourceSet {
                dependsOn(commonMain)
            }
            test.defaultSourceSet {
                dependsOn(main.defaultSourceSet)
            }
        }

        js {

            val main = compilations["main"]
            val test = compilations["test"]

            main.defaultSourceSet {
                dependsOn(commonMain)
            }
            test.defaultSourceSet {
                dependsOn(main.defaultSourceSet)
            }
        }
    }
}
