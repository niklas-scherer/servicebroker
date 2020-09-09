package com.sniperfuchs.servicebroker;

import com.sniperfuchs.servicebroker.model.*;
import com.sniperfuchs.servicebroker.repository.ServiceOfferingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootApplication
@EnableSwagger2
public class ServicebrokerApplication
{

    public static void main(String[] args) {
        SpringApplication.run(ServicebrokerApplication.class, args);
    }


    @Bean
    CommandLineRunner commandLineRunner(ServiceOfferingRepository serviceOfferingRepository)
    {
        return args -> {
            // Clearing DB
            serviceOfferingRepository.deleteAll();
            // Creating dummy service offering

            MaintenanceInfo maintenanceInfo = new MaintenanceInfo("2.1.1+abcdef",
                    "OS image update.\\nExpect downtime.");

            Map<String, Double> amount1 = new HashMap<>();
            amount1.put("usd", 99.0);
            Costs costs1 = new Costs(amount1, "MONTHLY");
            Map<String, Double> amount2 = new HashMap<>();
            amount2.put("usd", 0.99);
            Costs costs2 = new Costs(amount2, "1GB of messages over 20GB");

            PlanMetadata planMetadata1 = PlanMetadata.builder()
                    .costs(List.of(costs1, costs2))
                    .bullets(List.of("Shared fake server", "5 TB storage", "40 concurrent connections"))
                    .build();
            // TODO Complete metadata, maybe with its own objects

            //TODO add schemas

            ServicePlan fakePlan1 = ServicePlan.builder()
                    .id("test-plan-id1")
                    .name("fake-plan-1")
                    .description("Shared fake Server, 5tb persistent disk, 40 max concurrent connections.")
                    .free(false)
                    .maintenance_info(maintenanceInfo)
                    .metadata(planMetadata1)
                    .build();

            ServicePlan fakePlan2 = ServicePlan.builder()
                    .id("test-plan-id2")
                    .name("fake-plan-1")
                    .description("Shared fake Server, 5tb persistent disk, 40 max concurrent connections. 100 async.")
                    .free(false)
                    .maintenance_info(maintenanceInfo)
                    .build();




            Map<String, String> provider = new HashMap<>();
            provider.put("name", "The name");

            Map<String, String> listing = new HashMap<>();
            listing.put("imageUrl", "http://example.com/cat.gif");
            listing.put("blurb", "Add a blurb here");
            listing.put("longDescription", "A long time ago, in a galaxy far far away...");

            Map<String, Object> offeringMetadata = new HashMap<>();

            offeringMetadata.put("provider", provider);
            //offeringMetadata.put("listing", listing);
            offeringMetadata.put("displayName", "The Fake Service Broker");

            ServiceOffering testOffering = ServiceOffering.builder()
                    .name("test-service")
                    .description("Test Description")
                    .allow_context_updates(false)
                    .bindable(true)
                    .bindings_retrievable(true)
                    .instances_retrievable(true)
                    .tags(List.of("no-sql", "relational"))
                    .requires(List.of("route_forwarding"))
                    .metadata(offeringMetadata)
                    .plans(List.of(fakePlan1, fakePlan2))
                    .build();

            serviceOfferingRepository.save(testOffering);
        };
    }
}
