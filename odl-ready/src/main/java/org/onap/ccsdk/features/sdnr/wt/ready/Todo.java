/*
 * Copyright © 2021 highstreet technologies and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.onap.ccsdk.features.sdnr.wt.ready;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Todo {

    private String empId;
    private String firstName;
    private String lastname;
    private String dob;


        // Code required for getter and setter method, not needed to write.
    public static void main(String[] args) {
		Todo t = new Todo();
		t.setEmpId("123");
		t.setDob("12/1/1990");
		t.setFirstName("John");
		t.setLastname("K");
	}
}

