# `aio-lib-java` 

[![Maven Central](https://img.shields.io/maven-central/v/com.adobe.aio/aio-lib-java)](https://search.maven.org/artifact/com.adobe.aio/aio-lib-java)
[![Build](https://github.com/adobe/aio-lib-java/workflows/Snapshot%20Deploy/badge.svg)](https://github.com/adobe/aio-lib-java/actions?query=workflow%3ASnapshot%20Deploy)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

## Modules

This project is composed of Java Libraries :
* [`aio-lib-java-core`](./core)  holds the core models, builders, utilities used across the other libraries below,
* [`aio-lib-java-ims`](./ims) is a library wrapping a subset of [Adobe Identity Management System (IMS) API](https://www.adobe.io/authentication/auth-methods.html#!AdobeDocs/adobeio-auth/master/AuthenticationOverview/AuthenticationGuide.md)
* [`aio-lib-java-events-mgmt`](./events_mgmt) is a library wrapping [Adobe I/O Events Provider and Registration API](https://www.adobe.io/apis/experienceplatform/events/docs.html#!adobedocs/adobeio-events/master/api/api.md)
* [`aio-lib-java-events-ingress`](./events_ingress) is a library wrapping [Adobe I/O Events Publishing API](https://www.adobe.io/apis/experienceplatform/events/docs.html#!adobedocs/adobeio-events/master/api/eventsingress_api.md)
* [`aio-lib-java-events-journal`](./events_journal) is a library wrapping [Adobe I/O Events Journaling API](https://www.adobe.io/apis/experienceplatform/events/docs.html#!adobedocs/adobeio-events/master/api/journaling_api.md)
* as well as [`aem`](./aem) a sub-module, home of various Adobe I/O and AEM osgi connectors and packages

## Builds

This project is a [maven](https://maven.apache.org/) multi-modules project.

### Contributing

Contributions are welcomed! Read the [Contributing Guide](./.github/CONTRIBUTING.md) for more information.

### Licensing

This project is licensed under the Apache V2 License. See [LICENSE](LICENSE.md) for more information.

### Support 

This project is still in preliminary phase. No official support/SLA's is available for the SDK. 
