package com.company.linetownelection.screen.toggleelection;

import io.jmix.ui.screen.*;
import com.company.linetownelection.entity.ToggleElection;

@UiController("ToggleElection.browse")
@UiDescriptor("toggle-election-browse.xml")
@LookupComponent("toggleElectionsTable")
public class ToggleElectionBrowse extends StandardLookup<ToggleElection> {
}