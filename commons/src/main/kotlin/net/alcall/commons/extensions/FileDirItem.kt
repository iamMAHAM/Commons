package net.alcall.commons.extensions

import android.content.Context
import net.alcall.commons.models.FileDirItem

fun FileDirItem.isRecycleBinPath(context: Context): Boolean {
    return path.startsWith(context.recycleBinPath)
}
