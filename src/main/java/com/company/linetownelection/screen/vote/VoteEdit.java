package com.company.linetownelection.screen.vote;

import io.jmix.ui.screen.*;
import com.company.linetownelection.entity.Vote;

@UiController("Vote.edit")
@UiDescriptor("vote-edit.xml")
@EditedEntityContainer("voteDc")
public class VoteEdit extends StandardEditor<Vote> {
}