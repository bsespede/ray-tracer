package ar.edu.itba.grupo2.sampler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ar.edu.itba.grupo2.math.Point2D;
import ar.edu.itba.grupo2.utils.Constant;

public abstract class Sampler {

	protected final List<Point2D> squareSamples;
	protected final List<Point2D> diskSamples;
	protected final List<Integer> shuffledIndices;
	protected final int numSamples;
	protected final int numSets;
	protected int count;
	protected int jump;
	
	public Sampler(final int numSamples) {
		this.numSamples = numSamples;
		this.numSets = Constant.SAMPLES_SETS;
		this.squareSamples = new ArrayList<Point2D>(numSamples * Constant.SAMPLES_SETS);
		this.diskSamples = new ArrayList<Point2D>(numSamples * Constant.SAMPLES_SETS);
		this.shuffledIndices = new ArrayList<Integer>(numSamples * Constant.SAMPLES_SETS);
		generateSamples();
		mapSamplesToDisk();
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
		
		return squareSamples.get(jump + shuffledIndices.get(jump + count++ % numSamples));
	}
	
	public Point2D sampleUnitDisk() {
		return null;
	}
	
	private void mapSamplesToDisk() {
		float r, phi;
		Point2D sp = new Point2D(0, 0);
		
		for (int i = 0; i < diskSamples.size(); i ++) {
			
			sp.x = 2 * squareSamples.get(i).x - 1;
			sp.y = 2 * squareSamples.get(1).y - 1;
			
			if (sp.x > -sp.y) {
				if (sp.x > sp.y) {
					r = sp.x;
					phi = sp.y / sp.x;
				} else {
					r = sp.y;
					phi = 2 - sp.x / sp.y;
				}
			} else {
				if (sp.x < sp.y) {
					r = -sp.x;
					phi = 4 + sp.y / sp.x;
				} else {
					r = -sp.y;
					if (sp.y != 0) {
						phi = 6 - sp.x / sp.y;
					} else {
						phi = 0;
					}
				}
			}
			
			phi *= Constant.PI / 4.0f;
			diskSamples.add(i, new Point2D(r * (float) Math.cos(phi), r * (float) Math.sin(phi)));
		}
	}

	public int numSamples() {
		return numSamples;
	}
	
}
