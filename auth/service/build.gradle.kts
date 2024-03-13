dependencies {
    implementation(project(":auth:model"))
    implementation(project(":auth:use-case"))
    implementation(project(":auth:persist"))
    implementation(project(":auth:api"))

    api(project(":user:internal-api-client"))
//    implementation(project(":company:internal-api-client"))
}
