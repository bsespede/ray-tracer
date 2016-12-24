package ar.edu.itba.grupo2.sampler;

import ar.edu.itba.grupo2.math.Point2D;

public class Random extends Sampler {

	public Random(final int numSamples) {
		super(numSamples);
	}

	@Override
	public void generateSquareSamples() {
		for (int i = 0; i < numSets; i++) {
			for (int j = 0; j < numSamples; j++) {
				squareSamples.add(new Point2D((float) Math.random(), (float) Math.random()));
			}
		}
	}

}
