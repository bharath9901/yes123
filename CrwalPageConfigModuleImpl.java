package com.adobe.aem.guides.wknd.core.servlets;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

@Component(service = CrwalPageConfigModule.class,immediate = true)
@Designate(ocd = CrwalPageOSGiConfig.class)
public class CrwalPageConfigModuleImpl implements CrwalPageConfigModule{

    private String hostName;
    private String extensionsName;

    @Activate
    protected void activate(CrwalPageOSGiConfig crwalPageOSGiConfig){
    	hostName=crwalPageOSGiConfig.hostName();
    	extensionsName=crwalPageOSGiConfig.extensionsName();
    }
 
    @Override
    public String getHostName() {
        return hostName;
    }
    @Override
    public String getExtensionsName() {
        return extensionsName;
    }
}
