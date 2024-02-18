# Notification manager
### Service for sending emails.

## How to run
- Go to the email settings in the **Forwarding and POP/IMAP** section.
- Open file ***src/main/resources/application.yaml***.
- Choose **Enable POP for mail that arrived from now on**
- Write ***username*** (your email) and ***password*** in application.yaml.
- Check that in docker containers ***zookeeper*** and ***kafka1*** running.
- Run the service.