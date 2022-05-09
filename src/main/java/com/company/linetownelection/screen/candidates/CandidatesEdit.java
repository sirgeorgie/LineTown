package com.company.linetownelection.screen.candidates;

import io.jmix.ui.screen.*;
import com.company.linetownelection.entity.Candidates;

@UiController("Candidates.edit")
@UiDescriptor("candidates-edit.xml")
@EditedEntityContainer("candidatesDc")
public class CandidatesEdit extends StandardEditor<Candidates> {
}