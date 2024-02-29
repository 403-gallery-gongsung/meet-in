package com.gongsung.auth

interface Job : JobIdentity, JobProps {
    companion object {
        fun of(
            identity: JobIdentity,
            props: JobProps,
        ) = JobImpl(
            identity.jobIdentity,
            props.title,
            props.description,
        )
    }
}

interface JobIdentity {
    val jobIdentity: Long

    companion object {
        fun of(jobIdentity: Long): JobIdentity = JobIdentityImpl(jobIdentity)
    }
}

interface JobProps {
    var title: String
    var description: String

    companion object {
        fun of(
            title: String,
            description: String,
        ): JobProps = JobPropsImpl(title, description)
    }
}

class JobImpl(
    override val jobIdentity: Long,
    override var title: String,
    override var description: String,
) : Job

class JobIdentityImpl(
    override val jobIdentity: Long,
) : JobIdentity

class JobPropsImpl(
    override var title: String,
    override var description: String,
) : JobProps
