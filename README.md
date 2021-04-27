# Very basic service broker

The goal of this project is implementing a basic service broker which complies with the [Open Service Broker API specification](https://github.com/openservicebrokerapi/servicebroker/blob/v2.16/spec.md).
It will use [evoila's OSB Checker](https://github.com/evoila/osb-checker-kotlin) to test the endpoints and the [fabric8 Java Kubernetes Client](https://github.com/fabric8io/kubernetes-client) to deploy 
the provisioned services on a Kubernetes cluster (in this case a local minikube environment).
