package com.sniperfuchs.servicebroker.deployment;

import io.fabric8.kubernetes.api.model.HasMetadata;

import java.net.URI;
import java.util.List;

public interface ReleaseManager {
    public List<HasMetadata> install(URI resource);
    public void uninstall(String filePath);
    //public void delete();
    //public void upgrade();
}
