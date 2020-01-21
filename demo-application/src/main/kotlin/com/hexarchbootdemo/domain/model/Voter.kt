package com.hexarchbootdemo.domain.model

import java.util.*

// This class is an aggregate root for a Voter.  Yes, I know this class doesn't have child objects, but in a more complicated application it would.
class Voter(val id: UUID, val firstName: String, val lastName: String) {

    // Person domain logic goes here...

}