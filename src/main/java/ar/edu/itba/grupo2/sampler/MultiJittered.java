package ar.edu.itba.grupo2.sampler;

import ar.edu.itba.grupo2.math.Point2D;

public class MultiJittered extends Sampler {

	public MultiJittered(final int numSamples) {
		super(numSamples);
	}

	// Based on the "Ray tracing from the Ground up" implementation by Kevin Suffern
	public void generateSamples() {
		final int n = (int) Math.sqrt(numSamples);
		final float subcellWidth = 1.0f / numSamples;

		for (int j = 0; j < numSamples * numSets; j++) {
			squareSamples.add(new Point2D(0, 0));
		}

		// distribute points in the initial patterns
		for (int p = 0; p < numSets; p++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					Point2D point = new Point2D(0, 0);
					point.x = (i * n + j) * subcellWidth + (float) Math.random() * subcellWidth;
					point.y = (j * n + i) * subcellWidth + (float) Math.random() * subcellWidth;
					squareSamples.add(i * n + j + p * numSamples, point);
				}
			}
		}

		for (int p = 0; p < numSets; p++) {
			for (int i = 0; i < n; i++)	{
				for (int j = 0; j < n; j++) {
					// shuffle x coordinates
					int k = (int) (Math.random() * (n - j - 1) + j);
					float t = squareSamples.get(i * n + j + p * numSamples).x;
					squareSamples.get(i * n + j + p * numSamples).x = squareSamples.get(i * n + k + p * numSamples).x;
					squareSamples.get(i * n + k + p * numSamples).x = t;
					
					// shuffle y coordinates
					k = (int) (Math.random() * (n - j - 1) + j);
					t = squareSamples.get(j * n + i + p * numSamples).y;
					squareSamples.get(j * n + i + p * numSamples).y = squareSamples.get(k * n + i + p * numSamples).y;
					squareSamples.get(k * n + i + p * numSamples).y = t;
				}
			}
		}
	}

}
