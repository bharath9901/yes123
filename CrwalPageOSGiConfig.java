package com.adobe.aem.guides.wknd.core.servlets;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

    @ObjectClassDefinition(name="Crwal Page OSGi Configuration",
            description = "OSGi Configuration.")
    public @interface CrwalPageOSGiConfig {

        @AttributeDefinition(
                name = "Host Name",
                description = "Enter host name.",
                type = AttributeType.STRING)
        public String hostName() default "http://localhost:4502";

        @AttributeDefinition(
                name = "Extensions",
                description = "Add extensions.",
                type = AttributeType.STRING
        )
        String extensionsName() default ".html";
        
    }
