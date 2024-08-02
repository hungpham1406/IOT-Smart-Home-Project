import React, { useState } from 'react';
import style from './ModalForControlBlock.module.css';
function Modal({ isOpen, onClose, children }) {
    if (!isOpen) return null;

    return (
        <div className={style.modalOverlay}>
            <div className={style.modalContent}>
                <button onClick={onClose}>Close</button>
                {children}
            </div>
        </div>
    );
}
export default Modal;