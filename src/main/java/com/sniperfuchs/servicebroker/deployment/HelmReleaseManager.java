package com.sniperfuchs.servicebroker.deployment;

import hapi.chart.ChartOuterClass;
import hapi.release.ReleaseOuterClass;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import org.microbean.helm.Tiller;
import org.microbean.helm.chart.ZipInputStreamChartLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.zip.ZipInputStream;

public class HelmReleaseManager implements ReleaseManager {
    @Override
    public void install() {
        final String filepath = "/home/niklas/helm-charts/nginx-ingress-controller.zip";
        ChartOuterClass.Chart.Builder chart = null;
        try (final ZipInputStreamChartLoader chartLoader = new ZipInputStreamChartLoader()) {
            chart = chartLoader.load(new ZipInputStream(new FileInputStream(filepath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert chart != null;


        try (final DefaultKubernetesClient client = new DefaultKubernetesClient();
             final Tiller tiller = new Tiller(client);
             final org.microbean.helm.ReleaseManager releaseManager = new org.microbean.helm.ReleaseManager(tiller)) {

            final hapi.services.tiller.Tiller.InstallReleaseRequest.Builder requestBuilder = hapi.services.tiller.Tiller.InstallReleaseRequest.newBuilder();
            requestBuilder.setTimeout(300L);
            requestBuilder.setName("test-charts"); // Set the Helm release name
            requestBuilder.setWait(true); // Wait for Pods to be ready
            requestBuilder.setReuseName(true);
            // Install the loaded chart with no user-supplied overrides.
            // To override any values, call the requestBuilder.getValuesBuilder() method,
            // and add values to the resulting Builder, using its setRaw(String) method,
            // which takes a YAML string.
            //
            // Why setRaw(String)? Due to limitations in Tiller itself, Tiller will use
            // only the return value from Config.Builder#getRaw()
            // (https://microbean.github.io/microbean-helm/apidocs/hapi/chart/ConfigOuterClass.Config.Builder.html#getRaw--),
            // which is taken to be a YAML String representing the user-supplied overrides.
            // Tiller ignores any other values-related "getter" methods.


            final Future<hapi.services.tiller.Tiller.InstallReleaseResponse> releaseFuture = releaseManager.install(requestBuilder, chart);
            assert releaseFuture != null;
            final ReleaseOuterClass.Release release = releaseFuture.get().getRelease();
            //assert release != null;

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            // Sadly, this exception is expected due to microbean helm
        }
    }
}