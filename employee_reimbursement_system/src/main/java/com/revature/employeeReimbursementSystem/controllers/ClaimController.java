package com.revature.employeeReimbursementSystem.controllers;

import com.revature.employeeReimbursementSystem.entities.Claim;
import com.revature.employeeReimbursementSystem.services.ClaimService;
import io.javalin.http.Handler;

import java.util.List;

public class ClaimController {
    private ClaimService claimService;
    public ClaimController(ClaimService claimService){this.claimService = claimService;}
    public Handler createNewClaimRequest = ctx ->
    {
        System.out.println("Handling: new claim request.");
        Claim frontEndClaim = ctx.bodyAsClass(Claim.class);
        Claim selectedClaim = claimService.createNewClaim(frontEndClaim);
        if(frontEndClaim != null)
        {
            System.out.println("Request Handled: sending status: 200 (ok).");
            ctx.status(200);
        }
        else
        {
            System.out.println("Request Handled: sending status: 403 (forbidden).");
            ctx.status(403);
        }
    };
    public Handler getClaimsByAwardStatusRequest = ctx ->
    {
        System.out.println("Handling: get claims by award status request.");
        List<Claim> claimsForApproval = claimService.getClaimsForApproval();
        if(claimsForApproval != null)
        {
            System.out.println("Request Handled: sending status: 200 (ok).");
            ctx.sessionAttribute("claimsForApproval", claimsForApproval);
            ctx.json(claimsForApproval);
            ctx.status(200);

        }
        else
        {
            System.out.println("Request Handled: sending status: 403 (forbidden).");
            ctx.status(403);
        }
    };
    public Handler updateClaimById = ctx ->
    {
        System.out.println("Handling: update claim by id.");
        Claim updatedClaim = ctx.bodyAsClass(Claim.class);
        claimService.updateClaimByID(updatedClaim);
        if(updatedClaim != null)
        {
            System.out.println("Request Handled: sending status: 200 (ok).");
            ctx.status(200);
        }
        else
        {
            System.out.println("Request Handled: sending status: 403 (forbidden).");
            ctx.status(403);
        }
    };
    public Handler getClaimByIdRequest = ctx ->
    {
        System.out.println("Handling: get claim by id request.");
        int id = Integer.parseInt(ctx.pathParam("id"));
        Claim selectedClaim = claimService.getClaimByID(id);
        if(selectedClaim != null)
        {
            System.out.println("Request Handled: sending status: 200 (ok).");
            ctx.json(selectedClaim);
            ctx.status(200);
        }
        else
        {
            System.out.println("Request Handled: sending status: 403 (forbidden).");
            ctx.status(403);
        }
    };
    public Handler getClaimsByEmployeeIdRequest = ctx ->
    {
        System.out.println("Handling: get claim by employeeId request.");
        int employeeId = Integer.parseInt(ctx.pathParam("employeeId"));
        List<Claim> selectedClaims = claimService.getClaimsByEmployeeId(employeeId);
        if(selectedClaims != null)
        {
            System.out.println("Request Handled: sending status: 200 (ok).");
            ctx.json(selectedClaims);
            ctx.status(200);
        }
        else
        {
            System.out.println("Request Handled: sending status: 403 (forbidden).");
            ctx.status(403);
        }
    };
}
