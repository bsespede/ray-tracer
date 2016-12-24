package ar.edu.itba.grupo2.sampler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ar.edu.itba.grupo2.math.Point2D;
import ar.edu.itba.grupo2.utils.Constant;

public abstract class Sampler {

	protected final List<Point2D> samples;
	protected final List<Integer> shuffledIndices;
	protected final int numSamples;
	protected final int numSets;
	protected int count;
	protected int jump;
	
	public Sampler(final int numSamples) {
		this.numSamples = numSamples;
		this.numSets = Constant.SAMPLES_SETS;
		this.samples = new ArrayList<Point2D>(numSamples * Constant.SAMPLES_SETS);
		this.shuffledIndices = new ArrayList<Integer>(numSamples * Constant.SAMPLES_SETS);
		generateSamples();
		shuffleIndices();
	}
	
	protected abstract void generateSamples();
	
	protected void shuffleIndices() {
		List<Integer> indices = new ArrayList<Integer>(numSamples);
		
		for (int i = 0; i < numSamples; i++) {
			indices.add(i);
		}
		
		for (int j = 0; j < numSets; j++) {
			Collections.shuffle(indices);
			for (int k = 0; k < numSamples; k++) {
				shuffledIndices.add(indices.get(k));
			}
		}
	}
	
	public Point2D sampleUnitSquare() {
		if (count % numSamples == 0) {
			jump = (int) (Math.random() * numSets) * numSamples;
		}
		
		return samples.get(jump + shuffledIndices.get(jump + count++ % numSamples));
	}

	public int numSamples() {
		return numSamples;
	}
	
}
