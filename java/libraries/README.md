# fve-java-common-lib-parent
personal library package with common tools shared across various projects for java projects

- https://hackernoon.com/code-sharing-in-microservice-architecture-creating-your-own-common-library

- Core:
  - in it, we will store something basic for all microservices. 
  - For example, some of our basic exceptions are PlatformException or ApplicationException. 
  - We can also store there some basic data structures like Pair, Timer, 
  - and all similar things that can be used in any of our microservices regardless of their purpose.
- utils
  - in this module, we will store general-purpose utility classes. 
  - For example, classes for working with dates, prices, JSON, etc.
- database 
  - submodule will contain some useful things for working with the database. 
  - It can be custom serializers / deserializers of database fields and many other things. 
  - In our example, this submodule can be used by user-service and notification-service.
- logging 
  - in this, we will be able to describe the configuration to send all our logs to Splunk.
- client 
  - in this submodule, we can store everything that may be needed in microservices that interact with other APIs. 
  - It can be used as a gateway. 
- api 
  - here, we will store everything for services that will provide API methods. 
  - For example, configuration for autogeneration of documentation and the like. 
  - It can be used by user-service and gateway.
- testing
  - all stuff related to testing
- domain
  - domain stuff like enums, models, dtos, etc. that can be used in multiple places without special libs or so
- entities
  - jpa entities optionally usable
- Any other submodules you may need.
