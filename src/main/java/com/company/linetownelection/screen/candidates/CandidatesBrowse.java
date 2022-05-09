package com.company.linetownelection.screen.candidates;

import io.jmix.ui.screen.*;
import com.company.linetownelection.entity.Candidates;

@UiController("Candidates.browse")
@UiDescriptor("candidates-browse.xml")
@LookupComponent("candidatesesTable")
public class CandidatesBrowse extends StandardLookup<Candidates> {
}