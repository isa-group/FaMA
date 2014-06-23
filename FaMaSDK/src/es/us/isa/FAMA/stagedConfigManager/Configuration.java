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
package es.us.isa.FAMA.stagedConfigManager;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import es.us.isa.FAMA.models.featureModel.extended.GenericAttribute;
import es.us.isa.FAMA.models.variabilityModel.VariabilityElement;

public class Configuration {

	protected Map<VariabilityElement, Integer> elements;

	public Configuration(Map<VariabilityElement, Integer> elements) {
		this.elements = elements;
	}

	public Configuration() {
		this.elements = new HashMap<VariabilityElement, Integer>();
	}

	public Map<VariabilityElement, Integer> getElements() {
		return elements;
	}

	public void setElements(Map<VariabilityElement, Integer> elements) {
		this.elements = elements;
	}

	public void addElement(VariabilityElement ve, Integer integer){
		elements.put(ve, integer);
	}
	
	public String toString(){
		String res = "";
		Set<Entry<VariabilityElement,Integer>> entries = elements.entrySet();
		for (Entry<VariabilityElement,Integer> entry:entries){
			VariabilityElement v = entry.getKey();
			if (v instanceof GenericAttribute){
				GenericAttribute att = (GenericAttribute) v;
				res += att.getFullName().trim()+"="+entry.getValue()+" \n";
			}
			else{
				res += v.getName().trim()+"="+entry.getValue()+" \n";
			}
			
		}
		return res;
	}
	
}