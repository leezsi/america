package ar.edu.unq.americana.utils;

import java.net.URL;

public interface ResourceProvider {
	URL getResource(String name) throws Exception;
}
