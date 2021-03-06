package com.gigaspaces.webuitf.util.components.deployment;

/**
 * defines the interface a deployment window in the web ui should implement
 * @author elip
 *
 */
public interface IDeployWindow {
	
	/**
	 * inputs all deployment parameters
	 */
	public void sumbitDeploySpecs();
	
	/**
	 * deploys the PU onto the grid, this method must be followed be 
	 * the closeWindow method
	 */
	public void deploy();
	
	/**
	 * closes the deployment window
	 */
	public void closeWindow();

    /**
     * next
     */
    public AdvancedDeployment next();

    /**
     * prev
     */
    public AbstractDeployWindow prev();
}
