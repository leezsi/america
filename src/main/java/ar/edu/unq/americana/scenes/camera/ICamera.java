package ar.edu.unq.americana.scenes.camera;

import ar.edu.unq.americana.configs.Bean;

@Bean
public interface ICamera {

	double adjustX(double x);

	double adjustY(double y);

}
