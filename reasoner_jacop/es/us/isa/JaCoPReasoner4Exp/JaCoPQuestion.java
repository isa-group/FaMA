/*
	This file is part of FaMaTS.

    FaMaTS is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    FaMaTS is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with FaMaTS.  If not, see <http://www.gnu.org/licenses/>.

 */
package es.us.isa.JaCoPReasoner4Exp;

import JaCoP.search.ComparatorVariable;
import es.us.isa.FAMA.Benchmarking.PerformanceResult;
import es.us.isa.FAMA.Reasoner.Question;
import es.us.isa.FAMA.Reasoner.Reasoner;
import es.us.isa.JaCoPReasoner4Exp.JaCoPReasoner;

public class JaCoPQuestion implements Question {

	
	public Class<? extends Reasoner> getReasonerClass() {
		return es.us.isa.JaCoPReasoner4Exp.JaCoPReasoner.class;
	}
	

	protected ComparatorVariable heuristics;
	
	public JaCoPQuestion() {
		heuristics = null;
	}
	
	public void setHeuristics (ComparatorVariable heuristics) {
		this.heuristics = heuristics;
	}
	
	public void preAnswer(JaCoPReasoner r) {}
	public PerformanceResult answer(JaCoPReasoner r)  {return null;}
	public void postAnswer(JaCoPReasoner r) {}

}
