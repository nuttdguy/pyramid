import React from "react";


function InputFieldWithLabel(props) {
    return (
        <React.Fragment>
            <label 
                htmlFor={props.htmlFor}>
                {props.label}
            </label>

            <input type={props.type}
                className={props.clazz}
                id={props.id}
                maxLength={props.maxLength}
                pattern={props.pattern}
                required />
        </React.Fragment>
    )
}

export default InputFieldWithLabel;