package br.cin.ufpe.healthwatcher;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class LifeCycleListener implements PhaseListener {

	private static final long serialVersionUID = 7747124914537453944L;

	@Override
	public void afterPhase(PhaseEvent event) {
		//System.out.println("PHASE " + event.getPhaseId());
	}

	@Override
	public void beforePhase(PhaseEvent event) {

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
