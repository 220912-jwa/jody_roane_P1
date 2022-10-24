package com.revature.employeeReimbursementSystem.services;

import com.revature.employeeReimbursementSystem.dataAccessObjects.ClaimDAO;
import com.revature.employeeReimbursementSystem.entities.Claim;

import java.util.List;

public class ClaimService {
    private ClaimDAO claimDAO;

    public ClaimService(ClaimDAO claimDAO) {
        this.claimDAO = claimDAO;
    }

    public Claim createNewClaim(Claim frontEndClaim) {
        System.out.println("Starting claim service: Create New Claim.");
        Claim preSelectedClaim = claimDAO.createClaim(frontEndClaim);
        System.out.println("Front end claim input created this record:" + preSelectedClaim);
        System.out.println("A new claim was created: Returning claim with database id");
        return preSelectedClaim;
    }

    public List<Claim> getClaimsForApproval() {
        System.out.println("Starting claim service: Get claims for approval.");
        List<Claim> claims = claimDAO.getClaimsForApproval();
        System.out.println("Returning claims that have not been awarded.");
        return claims;

    }

    public void updateClaimByID(Claim updatedClaim) {
        System.out.println("Starting claim service: update claim by id.");
        claimDAO.updateClaimById(updatedClaim);
        System.out.println("Claim" + updatedClaim.getId() + ": Updated successfully.");
    }

    public Claim getClaimByID(int id)
    {
        System.out.println("Starting claim service: get claim by id.");
        Claim claim = claimDAO.geClaimById(id);
        System.out.println("This claim was retrieved:" + claim );
        System.out.println("Returning claim record.");
        return claim;
    }
    public List<Claim> getClaimsByEmployeeId(int employeeId)
    {
        System.out.println("Starting claim service: get claims by employeeId.");
        List<Claim> claims = claimDAO.getClaimsByEmployeeId(employeeId);
        System.out.println("These claims were retrieved:" + claims);
        return claims;
    }
}
