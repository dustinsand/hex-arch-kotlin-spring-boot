package com.hexarchbootdemo.domain.model

import java.util.UUID

// This class is an aggregate root for a Voter.  Yes, I know this class doesn't have child objects,
// but in a more complicated application it would.
class Voter(val id: UUID, val socialSecurityNumber: SocialSecurityNumber, val firstName: String, val lastName: String) {

    // Validation logic goes here...

    // Person domain logic goes here...
}
