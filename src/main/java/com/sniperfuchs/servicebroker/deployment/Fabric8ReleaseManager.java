package com.sniperfuchs.servicebroker.deployment;

import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.util.List;

@Service
public class Fabric8ReleaseManager implements ReleaseManager {

    @Override
    public void install(URI resource) {
        /*
            General idea:
                - Kubernetes YAML files are stored somewhere (locally or in some online repo)
                - They have to be either rendered with helm template, giving them a value file, or create them automatically some other way
                - Optional: Store the YAML files necessary locally so they can always be reached if an online repo dies. They are needed to uninstall
                - Use Fabric8's Kubernetes Client to apply the YAML file and store the metadata of that in the service instance
         */

        File resourceFile = null;

        if(resource.getScheme().equals("file")) {
            resourceFile = new File(resource);
        }
        else {
            //TODO: Get file from online repo
        }

        try(KubernetesClient kubernetesClient = new DefaultKubernetesClient()) {
            List<HasMetadata> metadataList = kubernetesClient.load(new FileInputStream(resourceFile)).createOrReplace();
        } catch (FileNotFoundException e) { // TODO: Or throw and use exception handler
            e.printStackTrace();
        }
    }
}
