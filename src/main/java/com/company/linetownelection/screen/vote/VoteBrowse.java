package com.company.linetownelection.screen.vote;

import io.jmix.ui.screen.*;
import com.company.linetownelection.entity.Vote;

@UiController("Vote.browse")
@UiDescriptor("vote-browse.xml")
@LookupComponent("votesTable")
public class VoteBrowse extends StandardLookup<Vote> {
}