package com.rewards.exception;

public class RewardsErrorMessage {
  private int statusCode;
  private String message;

  public RewardsErrorMessage(int statusCode, String message) {
    this.statusCode = statusCode;
    this.message = message;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public String getMessage() {
    return message;
  }

}