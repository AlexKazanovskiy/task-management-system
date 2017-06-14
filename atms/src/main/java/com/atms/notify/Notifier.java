package com.atms.notify;

import com.atms.model.Developer;
import com.atms.model.PasswordResetToken;
import com.atms.model.Task;

/**
 * @author Alex Kazanovskiy.
 */

public interface Notifier {

    void notifyDeveloper(Task task);

    void notifyCustomer(Task task);

    void restorePassword(Developer developer, PasswordResetToken token);
}
