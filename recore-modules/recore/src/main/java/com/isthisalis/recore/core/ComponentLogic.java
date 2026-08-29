package com.isthisalis.recore.core;

/**
 * Defines core logic for components.
 * 
 * @deprecated Scheduled for removal in 2.0.0. No longer maintained.
 */
@Deprecated(since = "1.0.0", forRemoval = true)
public interface ComponentLogic {

	/**
     * Cleans up resources.
     * 
     * @deprecated Scheduled for removal in 2.0.0.
     */
    @Deprecated(since = "1.0.0", forRemoval = true)
	void cleanup();

	/**
     * Updates the component state.
     * 
     * @deprecated Scheduled for removal in 2.0.0.
     */
	@Deprecated(since = "1.0.0", forRemoval = true)
	void update();
}