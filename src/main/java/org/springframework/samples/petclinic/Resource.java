package org.springframework.samples.petclinic;

import java.util.LinkedHashMap;

import javax.management.openmbean.CompositeData;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.openmbean.CompositeType;
import javax.management.openmbean.OpenDataException;
import javax.management.openmbean.OpenType;
import javax.management.openmbean.SimpleType;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource(objectName = "org.springbyexample.jmx:name=ServerManager")
public class Resource {
	LinkedHashMap<String, Long> items = new LinkedHashMap<>();

	@ManagedAttribute
	public int getSize() {
		return items.size();
	}

	@ManagedOperation
	public void addItem(String item, Long val) {
		items.put(item, val);
	}

	@ManagedOperation
	public long getItem(String item) {
		return items.get(item);
	}

	@ManagedAttribute
	public LinkedHashMap<String, Long> getItems() {
		return items;
	}

	@ManagedAttribute
	public CompositeData getComposites() throws OpenDataException {
		items.put("1test", 123L);
		items.put("2testtesttesttest", 123453453L);
		items.put("3tetesttesttestst", 123L);
		items.put("4tetesttestst", 123345L);
		items.put("5test", 134523L);
		items.put("6ttesttesttestest", 123453L);
		items.put("7ttesttestest", 123453453L);
		items.put("8ttesttesttesttesttestest", 123L);
		items.put("9test", 123453L);
		items.put("0ttesttesttesttestest", 123345345L);

		String[] itemNames = items.keySet().toArray(new String[items.size()]);
		OpenType[] itemTypes = new OpenType[] { SimpleType.LONG, SimpleType.LONG, SimpleType.LONG, SimpleType.LONG,
				SimpleType.LONG, SimpleType.LONG, SimpleType.LONG, SimpleType.LONG, SimpleType.LONG, SimpleType.LONG };
		CompositeType compositeType = new CompositeType("KeyValue", "kv desc", itemNames, itemNames, itemTypes);
		return new CompositeDataSupport(compositeType, itemNames, items.values().toArray());
	}
}