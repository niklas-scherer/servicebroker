package com.sniperfuchs.servicebroker.deployment;

import java.net.URI;

public interface ReleaseManager {
    public void install(URI resource);
    //public void uninstall();
    //public void delete();
    //public void upgrade();
}
