package ar.edu.itba.grupo2.sampler;

import ar.edu.itba.grupo2.math.Point2D;

public class Regular extends Sampler {

	public Regular(final int numSamples) {
		super(numSamples);
	}

	public void generateSamples() {
		final int n = (int) Math.sqrt(numSamples);

		for (int i = 0; i < numSets; i ++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					squareSamples.add(new Point2D((k + 0.5f) / n, (j + 0.5f) / n));
				}
			}
		}
	}

}
