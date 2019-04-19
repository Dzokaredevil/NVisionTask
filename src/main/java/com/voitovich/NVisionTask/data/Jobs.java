package com.voitovich.NVisionTask.data;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;

@JacksonXmlRootElement(localName = "jobs")
public class Jobs {
    @JacksonXmlElementWrapper(useWrapping=false)
    public ArrayList<Job> jobs;
}
