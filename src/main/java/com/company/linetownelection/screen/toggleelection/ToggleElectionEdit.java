package com.company.linetownelection.screen.toggleelection;

import io.jmix.ui.screen.*;
import com.company.linetownelection.entity.ToggleElection;

@UiController("ToggleElection.edit")
@UiDescriptor("toggle-election-edit.xml")
@EditedEntityContainer("toggleElectionDc")
public class ToggleElectionEdit extends StandardEditor<ToggleElection> {
}