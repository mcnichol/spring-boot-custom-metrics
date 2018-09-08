# Custom Metrics for Spring Boot using Micrometer

* ./gradlew clean build
* Push app to PWS / PCF with Metrics Forwarder installed
* Bind App to Metrics Forwarder
* With log-cache cli run `cf tail [your-app] -f`
* `curl [app url]`

You should see output showing the Timer and exposed Metrics from your application
